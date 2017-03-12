import com.kwetter.dao.KweetDAO_Impl;
import com.kwetter.dao.UserDAO_Impl;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.*;

/**
 * Created by Niek on 8-3-2017.
 */
public class DAOJpaImplTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterPUTest");
    EntityManager em;
    UserDAO_Impl userDao;
    DatabaseCleaner databaseCleaner;

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        userDao = new UserDAO_Impl(em);
    }

    @Test
    public void testCreateUser() {
        // create user
        Location location = new Location(1, 1, "Venlo");
        Role role = new Role("Moderater");
        User user = new User("NiekForTesting", "TEST", "Hello im test", location, "www.test.nl", role);
        em.getTransaction().begin();
        userDao.createUser(user);
        em.getTransaction().commit();
        // find user
        User finduserNiek = userDao.findUserByUserName("NiekForTesting");
        assertNotNull(finduserNiek);
    }

    @Test
    public void testEdit() {
        Location location = new Location(1, 1, "Venlo");
        //create user
        Role role = new Role("Moderater");
        User user = new User("NiekForTesting", "TEST", "Hello im test", null, "www.test.nl", role);
        em.getTransaction().begin();
        userDao.createUser(user);
        em.getTransaction().commit();

        // Verify that location property is not yet set of user object
        assertNull(user.getLocation());

        // edit user
        user.setLocation(location);
        em.getTransaction().begin();
        userDao.editUser(user);
        em.getTransaction().commit();

        //Find User through entity
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        User findNiek = em.find(User.class, user.getId());
        assertNotNull(findNiek);

        // verify eddited value of the persisted object
        assertEquals("Venlo", findNiek.getLocation().getCity());
    }

    @Test
    public void testRemove() {
        // create user
        Location location = new Location(1, 1, "Venlo");
        Role role = new Role("Moderater");
        User user = new User("NiekForTesting", "TEST", "Hello im test", location, "www.test.nl", role);
        em.getTransaction().begin();
        userDao.createUser(user);
        em.getTransaction().commit();

        //Find User
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        User findUser = em.find(User.class, user.getId());
        assertNotNull(findUser);

        // remove user
        em.getTransaction().begin();
        userDao.removeUser(findUser);
        em.getTransaction().commit();

        // try to find deleted user
        em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        User deletedUser = em.find(User.class, user.getId());
        assertNull(deletedUser);
    }


    @After
    public void tearDown() {
        try {
            databaseCleaner = new DatabaseCleaner(em);
            databaseCleaner.clean();
        } catch (SQLException ex) {
            Logger.getLogger(DAOJpaImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
