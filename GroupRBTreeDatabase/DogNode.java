// --== CS400 Project TWO File Header ==--
// Name: Gaven Wang
// Email: jwang2437@wisc.edu
// Team: red
// Group: AC
// TA: Ilay Raz
// Lecturer: Gary Dahl
// Notes to Grader: Node object initialization
interface DogNodeInterface{
    public String getName();
    public String getType();
    public int getBirthYear();
    public boolean vaccinationStatus();
    public boolean isRed();
    public String getVaccinationDate();
    public void setVaccinationStatus(boolean flag);
    public void setIsRed(boolean flag);
    public void setVaccinationDate(String date);
    public String toString();
}

public class DogNode implements Comparable<DogNode>, DogNodeInterface {
    private final String name;
    private final int birthYear;
    private final String type;
    private boolean isVaccinated;
    private String vaccinationDate;
    private boolean isRed;
    public String color;
    public DogNode leftChild;
    public DogNode rightChild;
    public DogNode parent;

    //Constructor requires name, birthYear and type
    public DogNode(String name, int birthYear, String type){
        this.name = name;
        this.birthYear = birthYear;
        this.type = type;
        this.isVaccinated = false;
        this.vaccinationDate = "";
        this.isRed = false;
    }

    //Set and get methods to help backend developer
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getBirthYear() {
        return this.birthYear;
    }

    @Override
    public boolean vaccinationStatus() {
        return this.isVaccinated;
    }

    @Override
    public boolean isRed() {
        return this.isRed;
    }

    @Override
    public String getVaccinationDate() {
        return this.vaccinationDate;
    }

    @Override
    public void setVaccinationStatus(boolean flag) {
        this.isVaccinated = flag;
    }

    //Provide color property for redBlackTree
    @Override
    public void setIsRed(boolean flag) {
        this.isRed = flag;
    }

    @Override
    public void setVaccinationDate(String date) {
        this.vaccinationDate = date;
    }

    @Override
    public String toString(){
        String result="Dog Name:"+this.name+" Year of birth:"+this.birthYear+" Type:"+this.type;
        return result;
    }

    //Making the node comparable in name--birth year--type order
    @Override
    public int compareTo(DogNode o) {
      if(this.name.hashCode()>o.name.hashCode()) {
        return 1;
      }
      else if(this.name.hashCode()<o.name.hashCode()) {
        return -1;
      }
      else {
        if(this.birthYear>o.birthYear) {
          return 1;
        }
        else if(this.birthYear<o.birthYear) {
          return -1;
        }
        else {
          if(this.type.hashCode()>o.type.hashCode()) {
            return 1;
          }
          else if(this.type.hashCode()<o.type.hashCode()){
            return -1;
          }
          else {
            return 0;
          }
        }
      }
    }
}


//Placeholders
class DogNodePlaceholderA implements DogNodeInterface{
    DogNodeInterface dog1 = new DogNode("Name1", 2018, "Type1");

    @Override
    public String getName() {
        return dog1.getName();
    }

    @Override
    public String getType() {
        return dog1.getType();
    }

    @Override
    public int getBirthYear() {
        return dog1.getBirthYear();
    }

    @Override
    public boolean vaccinationStatus() {
        return dog1.vaccinationStatus();
    }

    @Override
    public boolean isRed() {
        return dog1.isRed();
    }

    @Override
    public String getVaccinationDate() {
        return dog1.getVaccinationDate();
    }

    @Override
    public void setVaccinationStatus(boolean flag) {
        dog1.setVaccinationStatus(flag);
    }

    @Override
    public void setIsRed(boolean flag) {
        dog1.setIsRed(flag);
    }

    @Override
    public void setVaccinationDate(String date) {
        dog1.setVaccinationDate(date);
    }

}

class DogNodePlaceholderB implements DogNodeInterface{
    DogNodeInterface dog2 = new DogNode("Name2", 2020, "Type2");

    @Override
    public String getName() {
        return dog2.getName();
    }

    @Override
    public String getType() {
        return dog2.getType();
    }

    @Override
    public int getBirthYear() {
        return dog2.getBirthYear();
    }

    @Override
    public boolean vaccinationStatus() {
        return dog2.vaccinationStatus();
    }

    @Override
    public boolean isRed() {
        return dog2.isRed();
    }

    @Override
    public String getVaccinationDate() {
        return dog2.getVaccinationDate();
    }

    @Override
    public void setVaccinationStatus(boolean flag) {
        dog2.setVaccinationStatus(flag);
    }

    @Override
    public void setIsRed(boolean flag) {
        dog2.setIsRed(flag);
    }

    @Override
    public void setVaccinationDate(String date) {
        dog2.setVaccinationDate(date);
    }

}

class DogNodePlaceholderC implements DogNodeInterface{
    DogNodeInterface dog3 = new DogNode("Name3", 2015, "Type3");

    @Override
    public String getName() {
        return dog3.getName();
    }

    @Override
    public String getType() {
        return dog3.getType();
    }

    @Override
    public int getBirthYear() {
        return dog3.getBirthYear();
    }

    @Override
    public boolean vaccinationStatus() {
        return dog3.vaccinationStatus();
    }

    @Override
    public boolean isRed() {
        return dog3.isRed();
    }

    @Override
    public String getVaccinationDate() {
        return dog3.getVaccinationDate();
    }

    @Override
    public void setVaccinationStatus(boolean flag) {
        dog3.setVaccinationStatus(flag);
    }

    @Override
    public void setIsRed(boolean flag) {
        dog3.setIsRed(flag);
    }

    @Override
    public void setVaccinationDate(String date) {
        dog3.setVaccinationDate(date);
    }

}
