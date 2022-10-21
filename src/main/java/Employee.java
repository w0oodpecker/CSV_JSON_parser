public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return (id + " " + firstName + " " + lastName + " " + country + " " + age);
    }


    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj == this) {
            result = true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (int)id;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + age;
        return result;
    }


}
