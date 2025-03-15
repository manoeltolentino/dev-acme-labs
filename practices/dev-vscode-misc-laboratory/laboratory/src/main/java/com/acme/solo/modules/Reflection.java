package com.acme.solo.modules;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;

import com.acme.solo.models.PersonModel;

public class Reflection {

    public static void testReflection(){

        ArrayList<PersonModel> list = new ArrayList<>();
        //Set<PersonModel> list = new HashSet<>();

        list.add(new PersonModel(Long.valueOf(1), "Ana", "Centro"));
        list.add(new PersonModel(Long.valueOf(2), "Maria", "Floresta"));
        list.add(new PersonModel(Long.valueOf(3), "Joaquim", "Barreiro"));

       // getListByRawReflection(list, "id");
       getListByApacheCommons(list, "id");

    }

    private static void getListByApacheCommons(Iterable<?> list, String property){

        try{
            for (Object item : list) {
                System.out.println(PropertyUtils.getProperty(item, property));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        

    }

    private static void getListByRawReflection(Iterable list, String property){


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