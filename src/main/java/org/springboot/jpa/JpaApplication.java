package org.springboot.jpa;

import org.springboot.jpa.entity.Person;
import org.springboot.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        //save();
        System.out.println("************");
        list();
        System.out.println("************");
        findOne();
    }

    public void findOne() {
        Person person = null;
        Optional<Person> optionalPerson = personRepository.findById(1L);
        if (!optionalPerson.isEmpty()) {
            if (optionalPerson.isPresent()) {
                person = optionalPerson.get();
            }
        }
        System.out.println(person + "\n");

        personRepository.findOneLikeName("hn").ifPresent(System.out::println);
        System.out.println(person + "\n");

        personRepository.findByNameContaining("hn").ifPresent(System.out::println);
        System.out.println(person + "\n");

        personRepository.findByProgrammingLanguage("Java").stream().forEach(System.out::println);
    }

    public void list() {
        //List<Person> persons = (List<Person>) personRepository.findAll();
        //List<Person> persons2 = personRepository.buscarByProgrammingLanguageAndName("Python", "Pepe");

        List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Andrew");

        persons.stream().forEach(person -> {
            System.out.println(person);
        });

        List<Object[]> personsValues = personRepository.obtenerPersonDataByProgrammingLanguage("Java");
        personsValues.stream().forEach(person -> {
            System.out.println(person[0] + " es experto en: " + person[1]);
        });
    }
}
