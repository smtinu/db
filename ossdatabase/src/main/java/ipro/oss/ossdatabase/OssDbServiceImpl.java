package ipro.oss.ossdatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@Transactional
public class OssDbServiceImpl implements OssDbService 
{
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public E911StatusResponse getE911Status(String tnId) throws Exception{

		/*String orderNumber = "18671169";
		String ordeOrgNumber = "2";
		String orderSuppNumber = "0";
		String telePhoneNumber = "6162840484";
		Object Object = e911EIStatusRepository.findE911EIStatus(orderNumber, ordeOrgNumber, orderSuppNumber, telePhoneNumber, null, null, null, null, null, null);*/
		
		E911StatusResponse e911StatusResponse = new E911StatusResponse();
		
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("web_ossr_pkg.ossr_911_STP_r"); 
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(8, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(9, String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(10, String.class, ParameterMode.OUT);
        
        query.setParameter(1, "18671169");
        query.setParameter(2, "2");
        query.setParameter(3, "0");
        query.setParameter(4, "6162840484");
        
        query.execute();

        //Get output parameters
        String outMessage = (String) query.getOutputParameterValue(5);
        System.out.println("outMessage:"+outMessage);
        /*query.setParameter(5, 2);
        query.setParameter(6, 2);
        query.setParameter(6, 2);
        query.setParameter(7, 2);
        query.setParameter(8, 2);
        query.setParameter(9, 2);
        query.setParameter(10, 2);*/
        e911StatusResponse.setJobStepStatus((String) query.getOutputParameterValue(5));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
		String date = (String) query.getOutputParameterValue(6);
		java.util.Date parsedDate = dateFormat.parse(date);
        e911StatusResponse.setStatusEffectiveDate(new java.sql.Date(parsedDate.getDate()));
        e911StatusResponse.setValiJobStepStatus((String) query.getOutputParameterValue(8));
        /*String formattedDate1 = dateFormat.format((String) query.getOutputParameterValue(7));
        e911StatusResponse.setSentDate(formattedDate1);
        e911StatusResponse.setValiJobStepStatus((String) query.getOutputParameterValue(8));
        String formattedDate3 = dateFormat.format((String) query.getOutputParameterValue(9));
        e911StatusResponse.setValiStatusEffectiveDate(formattedDate3);
        String formattedDate4 = dateFormat.format((String) query.getOutputParameterValue(10));
        e911StatusResponse.setValiSentDate(formattedDate4);*/
		return e911StatusResponse;
	}
}