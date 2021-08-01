package vo;

public class Person {
    private String name;
    private Integer age;

    public Person() {
        System.out.println("==Person类被实例化==");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
