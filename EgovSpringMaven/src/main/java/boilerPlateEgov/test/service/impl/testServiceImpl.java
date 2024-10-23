package boilerPlateEgov.test.service.impl;

import boilerPlateEgov.test.service.testService;
import boilerPlateEgov.test.service.testVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("testService")
public class testServiceImpl extends EgovAbstractServiceImpl implements testService{
	
	@Resource(name = "testDAO")
	private testDAO testDAO;
	
	public List<testVO> selectAll() {
		return testDAO.selectAll();
	}

}
