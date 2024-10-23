package boilerPlateEgov.test.service.impl;

import boilerPlateEgov.test.service.testVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testDAO")
public class testDAO extends EgovAbstractMapper{
	
	public List<testVO> selectAll() {
		return selectList("testSelectAll");
	}

}
