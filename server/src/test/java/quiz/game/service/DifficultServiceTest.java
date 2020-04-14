package quiz.game.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Difficult;
import quiz.game.storage.DifficultStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DifficultServiceTest {
    @InjectMocks
    DifficultService difficultService;

    @Mock
    DifficultStorage difficultStorage;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllDifficult() {
        //given
        List<Difficult> difficults = Arrays.asList(
                new Difficult(1, "Easy", 1),
                new Difficult(2, "Normal", 2)
        );

        //when
        when(difficultStorage.getAllDifficult()).thenReturn(difficults);
        List<Difficult> result = difficultService.getAllDifficult();

        //expect
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Easy", result.get(0).getDifficultName());
        assertEquals(1, result.get(0).getDifficultFactor());
        assertEquals(2, result.get(1).getId());
        assertEquals("Normal", result.get(1).getDifficultName());
        assertEquals(2, result.get(1).getDifficultFactor());

        verify(difficultStorage, times(1)).getAllDifficult();
    }

    @Test
    public void getDifficultById() {
        //given
        Difficult difOne = new Difficult(1, "Easy", 1);

        //when
        when(difficultStorage.getDifficultById(1)).thenReturn(difOne);
        Difficult result = difficultService.getDifficultById(1);

        //expect
        assertEquals(1, result.getId());
        assertEquals("Easy", result.getDifficultName());
        assertEquals(1, result.getDifficultFactor());
    }

    @Test
    public void addDifficult() {
        //given
        List<Difficult> difficults = new ArrayList<>();
        Difficult difOne = new Difficult(1, "Easy", 1);
        Difficult difTwo = new Difficult(2, "Normal", 2);
        difficults.add(difOne);

        //when
        List<Difficult> result = difficultService.addDifficult(difOne);

        //expect
        verify(difficultStorage, times(1)).addDifficult(difOne);

    }
}