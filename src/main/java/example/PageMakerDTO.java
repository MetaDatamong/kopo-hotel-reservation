package example;

import java.util.ArrayList;
import java.util.List;

public class PageMakerDTO {
	
	private int startPage;
	
	private int endPage;
	
	private boolean prev, next;
	
	private int total;
	
	private int currPage;
	
	private int pageSize;
	
	private List<Integer> pageNumbers;

	public PageMakerDTO(int currPage, int pageSize, int total) {
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.total = total;
		
		this.endPage = (int)(Math.ceil((currPage + 1) / 10.0) * 10);
		
		this.startPage = this.endPage - 9;
		
		int realEnd = total;
		
		if (realEnd < endPage) {
			this.endPage = realEnd;
		}
		
		// 1보다 크면 prev => true
		this.prev = this.startPage > 1;
		
		// endPage 보다 realEnd가 작을때만 true
		this.next = this.endPage < realEnd;
		
		// 페이지 번호 리스트 생성
        this.pageNumbers = new ArrayList<>();
        for (int i = startPage - 1; i < endPage; i++) {
            this.pageNumbers.add(i);
        }
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }
	
	
	@Override
	public String toString() {
		return "PageMakerDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", currPage=" + currPage + ", pageSize=" + pageSize + "]";
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
