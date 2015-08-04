/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStuctures;

/**
 *
 * @author JanithaT
 */
public class Entity {

   private String recordID;
   private String firstName;
   private String lastName;
   private String city;
   
   public Entity(String rID,String Fname,String LName,String City){
       recordID = rID;
       firstName = Fname;
       lastName = LName;
       city = City;
       
   }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
   
   @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + getFirstName().length() + getLastName().length();
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        if (!(other.getFirstName() == getFirstName())
                || !(other.getLastName()== getLastName())
                || !(other.getCity()== getCity())) {
            return false;
        }
        return true;
    }
    
}
