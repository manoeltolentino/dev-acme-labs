import {Component} from '@angular/core';
import { HomeComponent } from './home/home.component';
import { ActivatedRoute, RouterModule } from '@angular/router';
import routeconfig from './routes';

@Component({
  selector: 'app-root',
  imports: [RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [ {provide: ActivatedRoute, useValue: routeconfig}]
})
export class AppComponent {
  title = 'default';
}
