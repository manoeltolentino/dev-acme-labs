import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

public class Reflection {

    public static void testReflection(){

        ArrayList<PersonModel> list = new ArrayList();
        
        list.add(new PersonModel(Long.valueOf(1), "Ana", "Centro"));
        list.add(new PersonModel(Long.valueOf(2), "Maria", "Floresta"));
        list.add(new PersonModel(Long.valueOf(3), "Joaquim", "Barreiro"));

        getSimpleList(list, "id");

    }

    private static void getSimpleList(Iterable list, String property){


        try{
            for (Object item : list) {
            
                Field field = item.getClass().getDeclaredField(property);
                field.setAccessible(true);
                Object value = field.get(item);

                System.out.println(value);
    
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }


}