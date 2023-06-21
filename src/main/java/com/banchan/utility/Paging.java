package com.banchan.utility;

public class Paging {
	// 페이징을 위한 클래스
	private int totalCount = 0; // 테이블 총 행 갯수
	private int totalPage = 0; // 전체 페이지 수

	private int pageNumber = 0; // 현재 페이지 번호
	private int pageSize = 0; // 한 페이지에 보여줄 행 갯수
	private int beginRow = 0; // 현재 페이지 기준 첫 행 랭킹 번호
	private int endRow = 0; // 현재 페이지 기준 끝 행 랭킹 번호

	private int beginPage = 0; // 페이지 링크 시작 번호
	private int endPage = 0; // 페이지 링크 끝 번호
	private int pageCount = 5; // 하단 페이지 표시 최댓 값

	private String pagingStatus = ""; // 상단 우측의 현재 페이지 현황
	private String pagingHtml = ""; // 하단 페이지 구성 요소의 하이퍼링크 문자열
	private String url = ""; // 게시물 페이지 command ( boList )

	private String mode = ""; // 검색 시 분류
	private String keyword = ""; // 검색 내용

	private String flowParameter = ""; // 페이지 이동시 같이 수반되는 파라미터 리스트
	
	public int getPageNumber() {return pageNumber;}
	public int getPageSize() {return pageSize;}
	public int getBeginRow() {return beginRow;}
	public int getEndRow() {return endRow;}
	public String getPagingStatus() {return pagingStatus;}
	public String getPagingHtml() {return pagingHtml;}
	public String getMode() {return mode;}
	public String getKeyword() {return keyword;}
	
	public String getFlowParameter() {return flowParameter;}
	
	public Paging(String _pageNumber, String _pageSize, int totalCount, String url, String mode, String keyword, boolean isGrid) {
		// isGrid true : 상품 목록 보기
		if(_pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")) {
			_pageNumber = "1";
		}
		this.pageNumber = Integer.parseInt(_pageNumber);
		
		if(_pageSize == null || _pageSize.equals("null") || _pageSize.equals("")) {
			if (isGrid==true) {
				_pageSize = "6"; // 2행 3열 격자 구조
			} else {
				_pageSize = "10";
			}
		}
		this.pageSize = Integer.parseInt(_pageSize);
		
		this.totalCount = totalCount;
		this.url = url;
		this.mode = mode;
		this.keyword = keyword;
		
		
		double _totalPage = Math.ceil((double)totalCount/pageSize);
		this.totalPage = (int)_totalPage;
		
		this.beginRow = (this.pageNumber - 1) * this.pageSize + 1;
		this.endRow = this.pageNumber * this.pageSize;
		
		this.beginPage = (this.pageNumber - 1)/this.pageCount * this.pageCount + 1;
		this.endPage = this.beginPage + this.pageCount - 1;
		
		if(endRow > totalCount) {
			endRow = totalCount;
		}
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		this.pagingStatus = "총 " + totalCount + "건[" + pageNumber + "/" + totalPage + "]";
		this.pagingHtml = this.getMakePagingHtml();
		
		this.flowParameter = "";
		this.flowParameter += "&pageNumber=" + this.pageNumber;
		this.flowParameter += "&pageSize=" + this.pageSize;
		this.flowParameter += "&mode=" + this.mode;
		this.flowParameter += "&keyword=" + this.keyword;
	}

	private String getMakePagingHtml() {
		String html = "";
		
		html += "<ul class='pagination justify-content-center'>";
		
		if (this.pageNumber <= this.pageCount) {
			// '맨처음', '이전' 항목이 존재하지 않는 영역
		} else {
			html += this.makeLiTag(String.valueOf(1), "<<");
			html += this.makeLiTag(String.valueOf(beginPage-1), "<");
		}
		
		for (int i = beginPage; i <= endPage; i++) {
			if(i==pageNumber) { // 현재 페이지
				html += "<li class='page-item active'><a class='page-link'><b><font color='white'>";
				html += i;
				html += "</font></b></a></li>";
			}else {
				html += this.makeLiTag(String.valueOf(i), String.valueOf(i));
			}
		}
		
		if (this.pageNumber >= (totalPage/pageCount*pageCount + 1)) {
			// '맨끝', '다음' 항목이 존재하지 않는 영역
		} else {
			html += this.makeLiTag(String.valueOf(endPage+1), ">");
			html += this.makeLiTag(String.valueOf(totalPage), ">>");
		}
		
		html += "</ul>";
		
		return html;
	}

	private String makeLiTag(String strPageNumber, String strLabel) {
		String result = "";
		
		result += "<li class='page-item'>";
		result += "<a class='page-link' href='";
		result += this.url;
		result += "&pageNumber=" + strPageNumber;
		result += "&pageSize=" + this.pageSize;
		result += "&mode=" + this.mode;
		result += "&keyword=" + this.keyword; 
		result += "'>" + strLabel;
		result += "</a></li>";
		
		return result;
	}

	@Override
	public String toString() {
		return "Paging [totalCount=" + totalCount + ", totalPage=" + totalPage + ", pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + ", beginRow=" + beginRow + ", endRow=" + endRow + ", beginPage="
				+ beginPage + ", endPage=" + endPage + ", pageCount=" + pageCount + ", pagingStatus=" + pagingStatus
				+ ", pagingHtml=" + pagingHtml + ", url=" + url + ", mode=" + mode + ", keyword=" + keyword + "]";
	}
}
