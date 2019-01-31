package ipro.oss.ossdatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest")
public class OSSDBController {
	

	@Autowired
	private OssDbService ossDbService;

	/**
	 * To get feature pack for a customerId and featureConfigId
	 * 
	 * @param customerId
	 * @param featureConfigId
	 * @return
	 */
	@GetMapping(value = "/ossdb/e911status/v1/{tnId}")
	public ResponseEntity<E911StatusResponse> getE911Status(@PathVariable String tnId) {
		E911StatusResponse e911StatusResponse = null;
		try {
			e911StatusResponse = ossDbService.getE911Status(tnId);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(e911StatusResponse, HttpStatus.OK);
	}

}
