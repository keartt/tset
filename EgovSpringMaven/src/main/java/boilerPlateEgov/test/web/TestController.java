package boilerPlateEgov.test.web;

import boilerPlateEgov.test.service.testService;
import boilerPlateEgov.test.service.testVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TestController {

	@Resource(name = "testService")
	private testService testService;

	@RequestMapping("/test.do")
	public String test(Model model){
		model.addAttribute("test", "test값");
		return "test";
	}

	@RequestMapping("/selectAll.do")
	@ResponseBody
	public List<testVO> testSelect() {
		return testService.selectAll();
	}
}
