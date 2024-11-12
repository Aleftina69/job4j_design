package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private String name;
    private int age;
    private boolean children;
    private Contact contact;
    @XmlElementWrapper(name = "families")
    @XmlElement(name = "family")
    private String[] family;

    public User() {
    }

    public User(String name, int age, boolean children, Contact contact, String[] family) {
        this.name = name;
        this.age = age;
        this.children = children;
        this.contact = contact;
        this.family = family;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + ", age=" + age
                + ", children=" + children
                + ", contact=" + contact
                + ", family=" + Arrays.toString(family)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final User user = new User("Ivan", 35, true, new Contact("11-111"), new String[]
                {"unknow", "unknow"});

        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
