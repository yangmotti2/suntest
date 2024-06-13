package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.HourlyVO;
import vo.SunVO;

public class SunDAO {
	
	SqlSession sqlSession;
	
	public SunDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 전체 데이터 조회
	public List<SunVO> selectList() {
		List<SunVO> list = sqlSession.selectList("sun.sun_list");
		return list;
	}
	
	// 조회한 리스트들 추가
	public int insert(HourlyVO vo) {
		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < vo.getTime().size(); i++) {
			String time = vo.getTime().get(i);
			String radi = vo.getDirect_radiation().get(i);
			map.put("time", time);
			map.put("radi", radi);
			res = res + sqlSession.insert("sun.insert", map);
		}
		
		return res;
	}
	
}














