package TestScenarios;

import CommonLibrary.CommonLibrary;
import CommonLibrary.DateUtility;
import Pages.*;
import TestBase.TestBase;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class BookTicket extends TestBase {
    LandingPage lp;
    SelectFlightPage sf;
    ReviewFlightPage rf;

    SelectFarePage sfare;

    ChooseAddOnPage cp;

    AddContactDetailsPage ac;

    AddTravelDetailsPage at;

    @Test (priority = 1)
    public void OpenSite(){
        LaunchBrowser();
        NavigateToURL();

        lp = new LandingPage();
        if (lp.CheckOtpPopup()){
            lp.ClickCancelOtp();
        }

        Assert.assertTrue(lp.CheckSearchFlightLabel());
    }

    @Test(dependsOnMethods = {"OpenSite"})
    public void SelectTripType(){
        Assert.assertTrue(lp.VerifyRouteWay());
    }

    @Test(priority = 2)
    public void SelectPassengerCountAndClassType(){
        boolean blnChild = false;
        //Add Adult travelers if its Zero
        boolean blnAdult = lp.AddTravellersCount(1, "Adult");

        //Add child based on Age Criteria
        if (Integer.parseInt(prop.getProperty("ChildAge")) > 2){
            blnChild = lp.AddTravellersCount(1, "children");
        }

        //Select Seat type as Economy
        lp.SelectSeatType();

        //Verify complete details
        if (lp.GetSelectedSeats().equalsIgnoreCase("1 Adult, 1 Child, Economy") && blnAdult && blnChild){
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 3)
    public void SelectSourceDestinationAndDateAndSearch(){
        DateUtility du = new DateUtility();
        lp.SelectSourceAndDestinationCity("Source", prop.getProperty("FlightSourceCity"));

        lp.SelectSourceAndDestinationCity("Destination", prop.getProperty("FlightDestinationCity"));

        //Here the logic is put to get date 14 days after
        lp.SelectTravelDate(du.GetTravelDate(14),"from");

        lp.SelectTravelDate(du.GetTravelDate(15),"to");

        lp.ClickSearchFlightButton();

        sf = new SelectFlightPage();

        Assert.assertTrue(sf.CheckNonStopButton());
    }

    @Test(priority = 4)
    public void SelectFlightFromGrid(){
        //Select Non-Stop and Cheapest Flight
        sf.ClickNonStopButton();

        sf.CheckFlightPriceIsLowest();

        sf.ClickBookButton();

        sf.HandleWindow();

        rf = new ReviewFlightPage();

        Assert.assertTrue(rf.CheckReviewPage());
    }

    @Test(priority = 5)
    public void ReviewYourItinerary(){
        Assert.assertEquals(rf.GetDestination1Data(),prop.getProperty("FlightDestinationCity"));

        Assert.assertEquals(rf.GetDestination2Data(),prop.getProperty("FlightSourceCity"));

        Assert.assertEquals(rf.GetSource1Data(),prop.getProperty("FlightSourceCity"));

        Assert.assertEquals(rf.GetSource2Data(),prop.getProperty("FlightDestinationCity"));

        rf.ClickContinueButton();

        sfare = new SelectFarePage();
        Assert.assertTrue(sfare.VerifyStandardFare());
    }

    @Test(priority = 6)
    public void SelectFareType(){
        sfare.ClickStandardFare();

        rf.ClickContinueButton();

        cp = new ChooseAddOnPage();
        Assert.assertTrue(cp.VerifyAddonPage());
    }

    @Test(priority = 7)
    public void SkipAddOn(){
        cp.ClickSkipThisStep();

        ac = new AddContactDetailsPage();

        Assert.assertTrue(ac.VerifyContactDetailsPage());
    }

    @Test(priority = 8)
    public void AddContactDetails(){
        ac.AddContactDetails();

        rf.ClickContinueButton();

        at = new AddTravelDetailsPage();

        Assert.assertTrue(at.VerifyTravellersPage());
    }

    @Test(priority = 9)
    public void EnterTravellersDetails(){
        at.EnterTravellersDetails();

        rf.ClickContinueButton();
    }


}
