package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.CorVO;

public class CorDAO {
	
	SqlSession sqlSession;
	
	public CorDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<CorVO> selectList() {
		List<CorVO> list = sqlSession.selectList("cor.select_list");
		return list;
	}
	
}













