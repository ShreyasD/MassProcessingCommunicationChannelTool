package com.ccprocessing.bluemix.rest;

import java.io.StringReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.ccprocessing.bluemix.constants.Contstants;
import com.husky.mock.pojo.ChannelStatusResult;

@Path("/FetchChannelService")
public class FetchChannelService {

	//@CrossOrigin(origin="*")
	@GET
	@Path("/fetchChannels")
	@Produces(MediaType.APPLICATION_JSON)
	public ChannelStatusResult fetchChannels() {
		try {
			//String url = "http://localhost:8080/HuskyStandAloneMock/ChannelAdminServlet?party=*&service=*&channel=*&action=status";
			
			CreateHuskyUrlConnection createHuskyUrlConnection = new CreateHuskyUrlConnection();
			String response = createHuskyUrlConnection.createUrlConnection(Contstants.urlChannelServlet + Contstants.urlGetChannels);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ChannelStatusResult.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ChannelStatusResult ChannelStatusResult = (ChannelStatusResult) jaxbUnmarshaller.unmarshal(new StringReader(response));

			
			return ChannelStatusResult;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
