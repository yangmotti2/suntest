package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.RadiVO;

public class RadiDAO {
	SqlSession sqlSession;
	
	public RadiDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//전체 조회
	public List<RadiVO> selecList(){
		List<RadiVO> list = sqlSession.selectList("sun.radi_list");
		return list;
	}
	
	// 해당 날짜 데이터 존재 여부 조회
	public List<RadiVO> radi_list_date(String date){
		List<RadiVO> list = sqlSession.selectList("sun.radi_list_date", date);
		return list;
	}
}
