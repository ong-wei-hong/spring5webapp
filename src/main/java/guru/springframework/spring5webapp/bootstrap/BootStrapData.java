package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Author rod = new Author("Rod", "Johnson");
        Book notEJB = new Book("J2EE Development without EJB", "393393");
        Publisher publisher = new Publisher("SFG Publishing", "St Petersburg", "FL", "state", "zip");

        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(rod);
        bookRepository.save(notEJB);
        publisherRepository.save(publisher);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        rod.getBooks().add(notEJB);
        notEJB.getAuthors().add(rod);
        publisher.getBooks().add(ddd);
        ddd.setPublisher(publisher);
        notEJB.setPublisher(publisher);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(rod);
        bookRepository.save(notEJB);
        publisherRepository.save(publisher);

        System.out.println("Bootstrap initialised");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of publisher's books: " + publisher.getBooks().size());
    }
}
