package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.UvVO;

public class UvDAO {
	SqlSession sqlSession;
	
	public UvDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<UvVO> list_select() {
		List<UvVO> list = sqlSession.selectList("uv.uv_list");
		return list;
	}
	
	// uv 데이터 추가
	public int list_insert(List<UvVO> list) {
		int res = 0;
		for(UvVO vo : list) {
			res = sqlSession.insert("uv.list_insert", vo);
		}
		return res;
	}
	
}
