import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.CalcModel;


class CalculatorTests
{

	CalcModel model;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		model = new CalcModel();
		
	}

	@Test
		void add() {
	        setNums(8, 3);
	        model.add();
	        assertEquals(11, getValue());
	    }
	@Test
		void subtract() {
			setNums(10,5);
			model.subtract();
			assertEquals(5,getValue());
		}
	@Test
		void multiply() {
			setNums(9,2);
			model.multiply();
			assertEquals(18,getValue());
		}
	@Test
		void divide() {
			setNums(10,5);
			model.divide();
			assertEquals(2,getValue());
		}
	
	private Double getValue()
	{
		return model.getResult().getValue();
	}

	private void setNums(int num1, int num2) {
        model.getNum1().setValue(num1);
        model.getNum2().setValue(num2);
    }

}