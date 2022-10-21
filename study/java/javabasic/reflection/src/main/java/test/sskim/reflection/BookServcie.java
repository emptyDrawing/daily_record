package test.sskim.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServcie {
    
    @Autowired
    BookRepository bookRepository;
    
}
