package kr.ac.kopo05.ctc.spring.board.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservationService {
	public Map<String, List> makeReservationTable(){
		
		
		
		
		
		return new LinkedHashMap<>();
	}
	
	public static void main(String[] args) {
		
		Map<String, List> rCalandar = new LinkedHashMap<>();
		
		// 오늘의 날짜
		LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        
        // 해당 월 가져오기
        System.out.println("현재 월: " + month);
        YearMonth yearMonth = YearMonth.of(year, month);
        
        // 첫번째 날
        LocalDate fisrtDate = yearMonth.atDay(1);
        
        // 마지막 날의 일
        int lastDay = yearMonth.atEndOfMonth().getDayOfMonth();
        
        for (int i = 0; i < lastDay; i ++) {
        	
        }
        // 현재 연도와 월의 마지막 날을 동적으로 구함
        YearMonth currentYearMonth = YearMonth.of(today.getYear(), month);
        LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth();
        
        System.out.println("해당 월의 마지막 날: " + lastDayOfMonth);
    }
}
