package com.malykhnik.bulletinboard.initialization;

import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.repository.InMemoryTopicDB;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@AllArgsConstructor
public class InMemoryDBInitializer implements ApplicationRunner {
    private final InMemoryTopicDB repo;
    @Override
    public void run(ApplicationArguments args) {
        List<Topic> init = new ArrayList<>();
        int nextId = 0;
        // Topic 1
        List<Message> topic1Messages = new ArrayList<>();
        topic1Messages.add(new Message(1, "John", "Hello, everyone!", Calendar.getInstance().getTime()));
        topic1Messages.add(new Message(2, "Alice", "Welcome to our community!", Calendar.getInstance().getTime()));
        for (int i = 0; i < topic1Messages.size(); i++) topic1Messages.get(i).setId(i+1);
        Topic topic1 = new Topic(1, "Introduction", topic1Messages);
        topic1.setId(nextId += 1);
        init.add(topic1);

        // Topic 2
        List<Message> topic2Messages = new ArrayList<>();
        topic2Messages.add(new Message(1, "Bob", "New feature announcement!", Calendar.getInstance().getTime()));
        topic2Messages.add(new Message(2, "Sarah", "Looking for feedback", Calendar.getInstance().getTime()));
        for (int i = 0; i < topic2Messages.size(); i++) topic2Messages.get(i).setId(i+1);
        Topic topic2 = new Topic(2, "Product Updates", topic2Messages);
        topic2.setId(nextId += 1);
        init.add(topic2);

        // Topic 3
        List<Message> topic3Messages = new ArrayList<>();
        topic3Messages.add(new Message(1, "Mark", "Important notice regarding security", Calendar.getInstance().getTime()));
        for (int i = 0; i < topic3Messages.size(); i++) topic3Messages.get(i).setId(i+1);
        Topic topic3 = new Topic(3, "Security Alert", topic3Messages);
        topic3.setId(nextId += 1);
        init.add(topic3);

        // Topic 4
        List<Message> topic4Messages = new ArrayList<>();
        topic4Messages.add(new Message(1, "Emily", "Event announcement: Save the date!", Calendar.getInstance().getTime()));
        topic4Messages.add(new Message(2, "Jake", "Event details and RSVP", Calendar.getInstance().getTime()));
        topic4Messages.add(new Message(3, "Olivia", "Agenda for the event", Calendar.getInstance().getTime()));
        for (int i = 0; i < topic4Messages.size(); i++) topic4Messages.get(i).setId(i+1);
        Topic topic4 = new Topic(4, "Upcoming Event", topic4Messages);
        topic4.setId(nextId += 1);
        init.add(topic4);

        // Topic 5
        List<Message> topic5Messages = new ArrayList<>();
        topic5Messages.add(new Message(1, "Tom", "Project update: Milestone achieved", Calendar.getInstance().getTime()));
        topic5Messages.add(new Message(2, "Linda", "Next steps and action items", Calendar.getInstance().getTime()));
        for (int i = 0; i < topic5Messages.size(); i++) topic5Messages.get(i).setId(i+1);
        Topic topic5 = new Topic(5, "Project Progress", topic5Messages);
        topic5.setId(nextId += 1);
        init.add(topic5);

        // Topic 6
        List<Message> topic6Messages = new ArrayList<>();
        topic6Messages.add(new Message(1, "Chris", "Feedback requested on new feature", Calendar.getInstance().getTime()));
        topic6Messages.add(new Message(2, "Sophia", "Bug report: Issue found in module", Calendar.getInstance().getTime()));
        topic6Messages.add(new Message(3, "Aiden", "Feature enhancement suggestions", Calendar.getInstance().getTime()));
        topic6Messages.add(new Message(4, "Emma", "Question about functionality", Calendar.getInstance().getTime()));
        for (int i = 0; i < topic6Messages.size(); i++) topic6Messages.get(i).setId(i+1);
        Topic topic6 = new Topic(6, "Feature Feedback", topic6Messages);
        topic6.setId(nextId += 1);
        init.add(topic6);

        repo.setTopics(init);
    }
}
