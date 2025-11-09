package hello.hello_spring.controller;

public class MemberForm {
    private String name;
    // memberForm의 name="name"과 연결된다

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
