package com.bharath.dating;

import com.bharath.dating.controllers.UserAccountController;
import com.bharath.dating.entities.Interest;
import com.bharath.dating.entities.UserAccount;
import com.bharath.dating.repos.InterestRepository;
import com.bharath.dating.repos.UserAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class DatingapiApplicationTests {

    public static final int ID = 123;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private InterestRepository interestRepository;

    @InjectMocks
    private UserAccountController userAccountController;

    @Test
    public void test_register_user_pass() {
        UserAccount userAccount = new UserAccount();
        UserAccount savedUserAccount = new UserAccount();
        savedUserAccount.setId(ID);
        when(userAccountRepository.save(userAccount)).thenReturn(savedUserAccount);
        UserAccount outputUserAccount = userAccountController.registerUser(userAccount);
        verify(userAccountRepository).save(userAccount);
        Assertions.assertNotNull(outputUserAccount);
        Assertions.assertNotNull(outputUserAccount.getId());
        Assertions.assertEquals(ID, outputUserAccount.getId());
    }

    @Test
    public void test_register_user_throw_exception() {
        UserAccount userAccount = new UserAccount();
        when(userAccountRepository.save(userAccount)).thenThrow(ConstraintViolationException.class);
        Assertions.assertThrows(ResponseStatusException.class, () -> userAccountController.registerUser(userAccount));
    }

    @Test
    public void test_update_interest() {
        Interest interest = new Interest();
        interest.setUserAccountId(ID);
        when(userAccountRepository.findById(ID)).thenReturn(Optional.of(new UserAccount()));
        Interest savedInterest = new Interest();
        savedInterest.setId(123);
        when(interestRepository.save(interest)).thenReturn(savedInterest);
        Interest outputInterest = userAccountController.updateInterest(interest);
        verify(userAccountRepository).findById(ID);
        verify(interestRepository).save(interest);
        Assertions.assertNotNull(outputInterest);
        Assertions.assertEquals(ID, outputInterest.getId());
    }

    @Test
    public void test_get_users() {
        ArrayList<UserAccount> userAccountArrayList = new ArrayList<UserAccount>();
        userAccountArrayList.add(new UserAccount());
        userAccountArrayList.add(new UserAccount());
        when(userAccountRepository.findAll()).thenReturn(userAccountArrayList);
        List<UserAccount> outputUsers = userAccountController.getUsers();
        verify(userAccountRepository).findAll();
        Assertions.assertNotNull(outputUsers);
        Assertions.assertEquals(2, outputUsers.size());
    }

    @Test
    public void test_delete_interest() {
        doNothing().when(interestRepository).deleteById(ID);
        userAccountController.deleteInterest(ID);
        verify(interestRepository).deleteById(ID);
    }

    @Test
    public void test_find_matches() {
        UserAccount account = new UserAccount();
        account.setId(123);
        account.setAge(25);
        account.setCity("Austin");
        account.setCountry("USA");
        ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
        userAccounts.add(new UserAccount());
        userAccounts.add(new UserAccount());
        when(userAccountRepository.findById(123)).thenReturn(Optional.of(account));
        when(userAccountRepository.findMatches(25, "Austin", "USA", 123)).thenReturn(userAccounts);
        List<UserAccount> matches = userAccountController.findMatches(123);
        verify(userAccountRepository).findById(123);
        verify(userAccountRepository).findMatches(25, "Austin", "USA", 123);
        Assertions.assertNotNull(matches);
        Assertions.assertEquals(2, matches.size());
    }
}
