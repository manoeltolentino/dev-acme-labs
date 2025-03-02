package com.acme.fw.vaadin.views.usermasterdetail;

import java.util.Optional;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.acme.fw.spring.model.User;
import com.acme.fw.spring.service.UserService;
import com.acme.fw.spring.utils.SpringUtils;
import com.acme.fw.vaadin.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import jakarta.annotation.security.PermitAll;

@PageTitle("Usuários")
@Route(value = "usuarios/:userID?/:action?(edit)", layout = MainLayout.class)
@Menu(order = 1, icon = LineAwesomeIconUrl.COLUMNS_SOLID)
@Uses(Icon.class)
@PermitAll
public class UserMasterDetailView extends Div implements BeforeEnterObserver {

	private final String USER_ID = "userID";
	private final String USER_EDIT_ROUTE_TEMPLATE = "usuarios/%s/edit";

	private final Grid<User> grid = new Grid<>(User.class, false);

	private TextField name;
	private EmailField email;
	private TextField login;
	private Checkbox active;
	private Checkbox confirmed;
	private TextField creation;
	private TextField lastAccess;

	private final Button cancel = new Button("Cancel");
	private final Button save = new Button("Save");
	private final Button delete = new Button("Delete");
	
	private ConfirmDialog confirmDialog = new ConfirmDialog();

	private final BeanValidationBinder<User> binder;

	private User user;

	private final UserService userService;

	public UserMasterDetailView(UserService userService) {
		this.userService = userService;
		addClassNames("master-detail-view");

		// Create UI
		SplitLayout splitLayout = new SplitLayout();

		createGridLayout(splitLayout);
		createEditorLayout(splitLayout);

		add(splitLayout);

		// Configure Grid
		grid.addColumn("login").setAutoWidth(true);
		grid.addColumn("name").setAutoWidth(true);
		LitRenderer<User> importantRenderer = LitRenderer.<User>of(
				"<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
				.withProperty("icon", active -> active.isActive() ? "check" : "minus")
				.withProperty("color", active -> active.isActive() ? "var(--lumo-primary-text-color)"
						: "var(--lumo-disabled-text-color)");

		grid.addColumn(importantRenderer).setHeader("Active").setAutoWidth(true);

		grid.setItems(query -> userService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

		// when a row is selected or deselected, populate form
		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() != null) {
				UI.getCurrent().navigate(String.format(USER_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
			} else {
				clearForm();
				UI.getCurrent().navigate(UserMasterDetailView.class);
			}
		});

		// Configure Form
		binder = new BeanValidationBinder<>(User.class);

		// Bind fields. This is where you'd define e.g. validation rules

		binder.bindInstanceFields(this);

		cancel.addClickListener(e -> {
			clearForm();
			refreshGrid();
		});

		save.addClickListener(e -> handleSave());

		delete.addClickListener(e -> handleDelete());
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		Optional<Long> userId = event.getRouteParameters().get(USER_ID).map(Long::parseLong);
		if (userId.isPresent()) {
			Optional<User> samplePersonFromBackend = userService.get(userId.get());
			if (samplePersonFromBackend.isPresent()) {
				populateForm(samplePersonFromBackend.get());
			}
			else {
				Notification.show(String.format("The requested samplePerson was not found, ID = %s", userId.get()),
						3000, Notification.Position.BOTTOM_START);
				// when a row is selected but the data is no longer available,
				// refresh grid
				refreshGrid();
				event.forwardTo(UserMasterDetailView.class);
			}
		}
	}

	private void createEditorLayout(SplitLayout splitLayout) {
		Div editorLayoutDiv = new Div();
		editorLayoutDiv.setClassName("editor-layout");

		Div editorDiv = new Div();
		editorDiv.setClassName("editor");
		editorLayoutDiv.add(editorDiv);

		FormLayout formLayout = new FormLayout();
		login = new TextField("Login");
		name = new TextField("Nome");
		email = new EmailField("Email");
		creation = new TextField("Criação");
		creation.setEnabled(false);
		lastAccess = new TextField("Último acesso");
		lastAccess.setEnabled(false);
		active = new Checkbox("Ativo");
		confirmed = new Checkbox("Confirmou");

		formLayout.add(name, email, login, creation, lastAccess, active, confirmed);

		editorDiv.add(formLayout);
		createButtonLayout(editorLayoutDiv);

		splitLayout.addToSecondary(editorLayoutDiv);
	}

	private void createButtonLayout(Div editorLayoutDiv) {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setClassName("button-layout");
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_WARNING);
		buttonLayout.add(save, delete, cancel);
		editorLayoutDiv.add(buttonLayout);
	}

	private void createGridLayout(SplitLayout splitLayout) {
		Div wrapper = new Div();
		wrapper.setClassName("grid-wrapper");
		splitLayout.addToPrimary(wrapper);
		wrapper.add(grid);
	}

	private void handleSave() {
		
		try {
			if (this.user == null) {
				this.user = new User();
			}
			binder.writeBean(this.user);
			
			this.user.setPassword(SpringUtils.encodeBCryptPassword(this.user.getPassword()));
			
			userService.save(this.user);
			
			clearForm();
			refreshGrid();
			Notification.show("Registro foi salvo");
			UI.getCurrent().navigate(UserMasterDetailView.class);
		} catch (ObjectOptimisticLockingFailureException exception) {
			Notification n = Notification.show("Error updating the data. Somebody else has updated the record while you were making changes.");
			n.setPosition(Position.MIDDLE);
			n.addThemeVariants(NotificationVariant.LUMO_ERROR);
		} catch (ValidationException validationException) {
			Notification.show("Failed to update the data. Check again that all values are valid");
		}

	}

	private void handleDelete() {

		if (this.user == null) {
			return;
		}

		confirmDialog.setHeader("Exclusão definitiva do registro");
		confirmDialog.setText("Confirma exclusão do usuário " + this.user.getLogin() + "?");

		confirmDialog.setCancelable(true);

		confirmDialog.setConfirmText("Delete");
		confirmDialog.setConfirmButtonTheme("error primary");
		confirmDialog.addConfirmListener(e -> {
			try {
				this.userService.deleteEntity(this.user);

				clearForm();
				refreshGrid();
				Notification.show("Registro foi excluído");
				UI.getCurrent().navigate(UserMasterDetailView.class);
			} catch (Exception ex) {
				Notification n = Notification.show("Error deleting the data." + ex.getStackTrace());
				n.setPosition(Position.MIDDLE);
				n.addThemeVariants(NotificationVariant.LUMO_ERROR);
			}

		});

		confirmDialog.open();

	}

	private void refreshGrid() {
		grid.select(null);
		grid.getDataProvider().refreshAll();
	}

	private void clearForm() {
		populateForm(null);
	}

	private void populateForm(User value) {
		this.user = value;
		binder.readBean(this.user);

	}
}
