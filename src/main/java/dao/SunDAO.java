package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.SunVO;

public class SunDAO {
	
	SqlSession sqlSession;
	
	public SunDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//밑에서는 원하는 CRUD 가져다 붙이삼
	public List<SunVO> selectList() {
		List<SunVO> list = sqlSession.selectList("sun.sun_list");
		return list;
	}
	
}














