package com.koitt.java.tx;

import java.sql.Connection;

public class BoardService {
	
	private BoardDao dao = new BoardDao();
	
	/*
	 * 롤백(Rollback): SQL문 실행 전 상태로 되돌리는 것
	 * 트랜젝션(Transaction): 여러개의 SQL문을 하나의 작업단위로 묶은 것
	 * 트랜젝션 처리를 한다는 것: 여러개의 SQL문을 하나의 작업단위로 묶었는데
	 * 그 여러개 SQL문 중 하나에서 문제가 발생한다면 롤백을 호출하여
	 * 이전상태로 되돌리도록 처리하는 것을 트랜젝션 처리했다라고 한다.
	 */
	public void add(Board board) {
		Connection conn = null;
		
		try {
			// 트랜젝션 처리영역 시작부분
			conn = DBUtil.getInstance().getConnection();
			
			/*
			 *  자동 Commit을 막기위해 false 설정
			 *  Commit: 테이블에 최종적으로 SQL 실행한 결과를 반영하는 것
			 *  false로 설정했기 때문에 우리가 직접 커밋을 호출해야 한다.
			 */
			conn.setAutoCommit(false);
			dao.drop(conn);	// Drop과 Alter는 롤백이 되지 않는다.
			Integer no = dao.getBoardNo(conn);
			board.setNo(no);
			dao.insert(conn, board);
			
			// 마지막으로 SQL 실행한 결과를 반영
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();	// 트랜젝션 처리 영역 끝부분
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
