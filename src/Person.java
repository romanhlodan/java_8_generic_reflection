public class Person<I,N,A,M> {

    private I id;
    private N name;
    @Marker(message1 = "Malo rokiv", message2 = "Vse OK")
    private A age;
    private M married;

    public Person() {
    }

    public Person(I id, N name, A age, M married) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.married = married;
    }

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public N getName() {
        return name;
    }

    public void setName(N name) {
        this.name = name;
    }

    public A getAge() {
        return age;
    }

    public void setAge(A age) {
        this.age = age;
    }

    public M getMarried() {
        return married;
    }

    public void setMarried(M married) {
        this.married = married;
    }


}
