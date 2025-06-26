package com.example.my_vaadin_24;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@Route("home")
@RouteAlias("")
public class HomeView extends VerticalLayout {

    public HomeView() {

        this.setMargin(true);

        TextField firstName = new TextField("first name");
        TextField lastName = new TextField("last name");
        IntegerField age = new IntegerField("age");


        firstName.focus();

        Binder<Person> binder = new Binder<Person>(Person.class);

        binder.forField(firstName)
                .withValidator(name -> name.length() > 2, "too short!")
                .bind(Person::getFirstName, Person::setFirstName);

        binder.forField(lastName)
                .withValidator(name -> name.contains("k"), "missing k")
                .bind(Person::getLastName, Person::setLastName);

        binder.forField(age)
                .withValidator(n -> n > 0 && n < 100, "invalid age!")
                .bind(Person::getAge, Person::setAge);

        Button btn = new Button("Show");

        btn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btn.setIcon(VaadinIcon.LOCATION_ARROW_CIRCLE_O.create());
        btn.setIconAfterText(true);

        Person person = new Person();

        btn.addClickListener(e -> {
            try {
                if (binder.validate().isOk()) {
                    binder.writeBean(person);
                    Notification.show(person.getFirstName());
                    System.out.println("firstName: " + person.getFirstName());
                    System.out.println("firstName: " + person.getLastName());
                    System.out.println("firstName: " + person.getAge());
                } else {
                    List<BindingValidationStatus<?>> xs = binder.validate().getFieldValidationErrors();
                    System.out.println("==>" + xs);
                }
            } catch(ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });

        btn.addClickShortcut(Key.ENTER);

        add(firstName, lastName, age, btn);

    }
}