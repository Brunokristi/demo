package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class SaveData implements Serializable {

    private static final long serialVersionUID = 1L;
    public SaveData() {
    }
    public static void writeData(Place newPlace) {
        String path = newPlace.getCity() + ".txt";
        File file = new File(path);
        FileWriter fr = null;

        try {
            fr = new FileWriter(file);
            fr.write(newPlace.getCreatorPlace() + "\n");

            fr.write(newPlace.getCaption() + "\n");

            fr.write(newPlace.getCity() + "\n");

            int count = newPlace.getActivities().length;
            for (int i = 0; i < count; i++) {
                fr.write(newPlace.getActivities()[i] + ", ");
            }
            fr.write("\n");

            fr.write(newPlace.getCost() + "\n");

            count = newPlace.getInvited().length;
            for (int i = 0; i < count; i++) {
                fr.write(newPlace.getInvited()[i] + ", ");
            }
            fr.write("\n");


            for (int i = 0; i < count; i++) {
                fr.write(newPlace.getGoing()[i] + ", ");
            }

        } catch (IOException var19) {
            var19.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }

        }
        /*Messages.receivers = invited;
        Messages.sender=creator;
        Messages.message=("You have been invited to: " + caption + " in " + city);
        Messages.sendMessage();
        System.out.println("Friends invited!");
        Location.location=city;
        GraphImplementation.nextCities();
    }*/
    }

    public static void Put(User newPerson) {

        File file = new File(newPerson.getUser()+ ".txt");
        FileWriter fr = null;

        try {
            fr = new FileWriter(file);
            fr.write(newPerson.getUser() + "\n");
            fr.write(newPerson.getPassword() + "\n");


            int count= newPerson.getInvitedTo().length;
            if(count==0)
            {
                fr.write(", \n");
            }
            else {
                for (int i = 0; i < count; i++) {
                    fr.write(newPerson.getInvitedTo()[i] + ", ");
                }
                fr.write("\n");
            }

            count= newPerson.getCreated().length;
            if (count==0)
            {
                fr.write(", \n");
            }
            else
            {
                for(int i=0; i<count; i++)
                {
                    fr.write(newPerson.getCreated()[i]+", ");
                }
                fr.write("\n");
            }

            if(newPerson.getCreator()==true)
            {
                fr.write("1");
            }
            else
            {
                fr.write("0");
            }



        } catch (
                IOException var19) {
            var19.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }

        }
    }
}
