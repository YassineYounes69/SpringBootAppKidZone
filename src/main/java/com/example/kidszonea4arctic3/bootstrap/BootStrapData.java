package com.example.kidszonea4arctic3.bootstrap;

import com.example.kidszonea4arctic3.models.Parent;
import com.example.kidszonea4arctic3.repositories.ChildRepository;
import com.example.kidszonea4arctic3.repositories.ParentRepository;

import com.example.kidszonea4arctic3.services.ParentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final ParentService parentService;
    Parent parent;
    public BootStrapData(ParentRepository parentRepository, ChildRepository childRepository, ParentService parentService) {
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
        this.parentService = parentService;
    }

    @Override
    public void run(String... args) throws Exception {


     //   Parent parent1 = new Parent("yassine.younes@esprit.tn","123","Yassine","Younes",56160377,false);

      //  parentRepository.save(parent1);
      //  Child child1 = new Child("Amine","Gongi",3,parent1);

       // childRepository.save(child1);
        //parent1.getChildren().add(child1);

       // Child child2 = new Child("Mohamed","Lahsoumi",4,parent1);
      //  childRepository.save(child2);

       // parent1.getChildren().add(child2);



        //parentRepository.save(parent1);


        System.out.println("started bootstrap");

        // System.out.println(parent1.toString());
       // System.out.println(child1.toString());
        //System.out.println(child2.toString());
        //System.out.println(parent1.toString());
    }
}
