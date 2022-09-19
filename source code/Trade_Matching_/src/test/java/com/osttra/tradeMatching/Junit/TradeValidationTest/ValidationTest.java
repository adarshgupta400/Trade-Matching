package com.osttra.tradeMatching.Junit.TradeValidationTest;



import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;



import com.osttra.tradeMatching.constant.Constants;
import com.osttra.tradeMatching.exception.NoTradeValidation;
import com.osttra.tradeMatching.models.PartyValidation;
import com.osttra.tradeMatching.models.TradeData;
import com.osttra.tradeMatching.repo.TradeRepo;
import com.osttra.tradeMatching.repo.ValidationRepo;
import com.osttra.tradeMatching.utils.TradeValidationImpl;




import static org.junit.Assert.assertFalse;



import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;



import java.time.LocalDate;



import java.util.Date;
import java.util.List;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;




@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ValidationTest {
    
    @Mock
    TradeRepo tradeRepo;
    @Mock
    ValidationRepo validationRepo;
    
    @InjectMocks
    TradeValidationImpl tradeValidation;
    
    TradeData trade1;
    PartyValidation partyValidation1;
    PartyValidation partyValidation2;
    String returnMsg;
    
    @BeforeEach
    void init() {
        trade1 = new TradeData(null, "SBIM", "01", "SBIM01", "SBI", "HDFCM", "HDFC", "SBI MUMBAI", "SBI MUMBAI",
                LocalDate.now(), LocalDate.now(), "Bonds", 1080, LocalDate.now(), "INR", "SBIM", "HDFCM",
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), 1, "Unconfirmed");
        partyValidation1=new PartyValidation(null,"SBI","SBIM","SBI MUMBAI");
        partyValidation2=new PartyValidation(null,"HDFC","HDFCM","HDFC MUMBAI");        
    }
    
    @Test
    @DisplayName("Check Valid Trade with positive test case")
    public void testCheckValidTradeWithPosiive() throws NoTradeValidation {
        when(this.tradeRepo.findByTradeRefNum(trade1.getTradeRefNum())).thenReturn(null);
        when(this.validationRepo.findByPartyAndPartyInstitutionAndPartyFullName("SBIM", "SBI", "SBI MUMBAI")).thenReturn(partyValidation1);
        when(this.validationRepo.findByPartyAndPartyInstitutionAndPartyFullName("HDFCM", "HDFC", "HDFC MUMBAI")).thenReturn(partyValidation2);
        when(this.validationRepo.findByParty(trade1.getBuyer())).thenReturn(partyValidation2);
        when(this.validationRepo.findByParty(trade1.getSeller())).thenReturn(partyValidation1);
        String returnMsg=tradeValidation.checkValidTrade(trade1);
        System.out.println(returnMsg);
        assertTrue(returnMsg.equals(Constants.ERROR1));
        
    }
    
    @Test
    @DisplayName("Check Valid Trade with negative test case")
    public void testCheckValidTradeWithNegative() throws NoTradeValidation {
        when(this.tradeRepo.findByTradeRefNum(trade1.getTradeRefNum())).thenReturn(null);
        when(this.validationRepo.findByPartyAndPartyInstitutionAndPartyFullName("SBIM", "SBI", "SBI MUMBAI")).thenReturn(partyValidation1);
        when(this.validationRepo.findByPartyAndPartyInstitutionAndPartyFullName("HDFCM", "HDFC", "HDFC MUMBAI")).thenReturn(partyValidation2);
        when(this.validationRepo.findByParty(trade1.getBuyer())).thenReturn(partyValidation2);
        when(this.validationRepo.findByParty(trade1.getSeller())).thenReturn(partyValidation1);
        returnMsg=tradeValidation.checkValidTrade(trade1);
        System.out.println(returnMsg);
        assertFalse(returnMsg.equals(Constants.VALID));
        
    }
    
    @Test
    @DisplayName("Check Valid Trade with exception test case")
    public void testCheckValidTradeWithException() {
        
        when(this.tradeRepo.findByTradeRefNum(trade1.getTradeRefNum())).thenReturn(null);
        when(this.validationRepo.findByPartyAndPartyInstitutionAndPartyFullName("SBIM", "SBI", "SBI MUMBAI")).thenReturn(partyValidation1);
        when(this.validationRepo.findByPartyAndPartyInstitutionAndPartyFullName("HDFCM", "HDFC", "HDFC MUMBAI")).thenReturn(partyValidation2);
        when(this.validationRepo.findByParty(trade1.getBuyer())).thenReturn(partyValidation2);
        when(this.validationRepo.findByParty(trade1.getSeller())).thenReturn(partyValidation1);
        assertDoesNotThrow(() -> {
         returnMsg=tradeValidation.checkValidTrade(trade1);
        });        
    }
    
    @AfterEach
    void clear() {
        trade1=null;
        partyValidation1=null;
        partyValidation2=null;
        returnMsg=null;
    }
    



}
