package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

   private ClinicCalendar calendar;

   @BeforeAll
   static void testClassSetup(){
      System.out.println("Before all...");
   }
   @BeforeEach
   void init(){
      System.out.println("Before each...");
      calendar = new ClinicCalendar(LocalDate.of(2022,11,1));
   }

   @Test
   void allowEntryOfAnAppointment() {
      System.out.println("entry of appointment...");
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);
      assertEquals("Jim", enteredAppt.getPatientFirstName());
      assertEquals("Weaver", enteredAppt.getPatientLastName());
      assertEquals(Doctor.avery, enteredAppt.getDoctor());
      assertEquals("9/1/2018 02:00 PM",
         enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)));
   }
   @Test
   void returnTrueForHasAppointmentsIfThereAreAppointments(){
      calendar.addAppointment("Jim","Weaver","avery","11/1/2022 2:00 pm");
      assertTrue(calendar.hasAppointment(LocalDate.of(2022,11,1)));
   }

   @Test
   void returnFalseForHasAppointmentsIfThereAreNone(){
      assertFalse(calendar.hasAppointment(LocalDate.of(2022,11,1)));
   }


   @Test
   void returnCurrentDayAppointments() {
      calendar.addAppointment("Rob","Pratte","avery",
              "11/1/2022 3:00 pm");
      calendar.addAppointment("Rob","Pratte","avery",
              "11/1/2022 2:00 pm");
      calendar.addAppointment("Rob","Pratte","avery",
              "11/21/2022 3:00 pm");
      assertEquals(2,calendar.getTodayAppointments().size());

   }

   @AfterEach
      void tearDownEachTest(){
         System.out.println("After each...");
      }
      @AfterAll
      static void tearDownAllTests(){
      System.out.println("Tearing down...");
      }


}