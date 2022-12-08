package test;

import com.example.event.AddEvent;
import org.junit.Assert;
import org.junit.Assert.*;

import java.time.LocalDate;

public class AddEventTest {

    AddEvent addEvent = new AddEvent();


    @org.junit.Test
    public void add(){
        LocalDate ld = LocalDate.now();

        String result1 = addEvent.add("", null, null, "", "", "","", "",
                "", "", "", "", "","", "");
     //   Assert.assertEquals("Вы не выбрали мероприятие", result1);

        String result2 = addEvent.add("Имя", null, null, "", "", "","", "",
                "", "", "", "", "","", "");
     //   Assert.assertEquals("Вы не выбрали дату", result2);

        String result3 = addEvent.add("Имя111", ld, ld, "10", "", "","", "",
                "", "", "", "", "","", "");
     //   Assert.assertEquals("Мероприятие добавлено", result3);

        String result4 = addEvent.add("Юзабилити", ld, ld, "10", "", "","", "",
                "", "", "", "", "","", "");
     //   Assert.assertEquals("Номер не выбран", result4);

        String result5 = addEvent.add("Юзабилити", ld, ld, "10", "15", "15","", "",
                "", "", "", "", "","", "");
      //  Assert.assertEquals("Имя активности не выбрано", result5);

        String result6 = addEvent.add("Юзабилити", ld, ld, "10", "15", "1","2", "3",
                "", "", "", "", "","", "");
    //    Assert.assertEquals("День активности не выбран", result6);

        String result7 = addEvent.add("Юзабилити", ld, ld, "10", "15", "1","2", "3",
                "1", "2", "3", "", "","", "");
    //    Assert.assertEquals("Время первой активности не выбрано", result7);

        String result8 = addEvent.add("Юзабилити", ld, ld, "10", "15", "1","2", "3",
                "1", "2", "3", "1", "","", "");
      //  Assert.assertEquals("Время второй активности не выбрано", result8);

        String result9 = addEvent.add("Юзабилити", ld, ld, "10", "15", "1","2", "3",
                "1", "2", "3", "1", "2","", "");
     //   Assert.assertEquals("Время третьей активности не выбрано", result9);

        String result10 = addEvent.add("Юзабилити", ld, ld, "10", "15", "1","2", "3",
                "1", "2", "3", "1", "2","3", "");
     //   Assert.assertEquals("Модератор не выбран", result10);









    }







}