package com.team13.petassist.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import com.team13.petassist.entity.Contact;
import com.team13.petassist.interfaces.IContact;

@ExtendWith(MockitoExtension.class)
public class ContactRepoTest {
	
    @Mock
    private IContact mockIContact;
    
    @Mock
    private Contact mockContact;
    
    @Mock
    private ContactRepo mockContactRepo;
    @Test
    void ContactPageAccess() throws SQLException {
    	when(mockIContact.getContactDetails()).thenReturn(mockContact);
		assertNotNull(mockIContact.getContactDetails());
    }

}
