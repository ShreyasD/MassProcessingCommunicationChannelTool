package com.ccprocessing.bluemix.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ccprocessing.bluemix.constants.Contstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.husky.mock.pojo.Channel;
import com.husky.mock.pojo.ChannelStatusResult;

@Path("/ModifyChannelService")
public class ModifyChannelService {

	@POST
	@Path("/modifyChannels")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ChannelStatusResult modifyChannels(ChannelStatusResult channelStatusResult) throws JsonProcessingException {
		
		
		
		//String url = "http://localhost:8080/HuskyStandAloneMock/ChannelAdminServlet?party=*&service=*&channel=*&action=status";
		for(Channel channel : channelStatusResult.getChannels().getChannel()){
			StringBuilder sbUrl = new StringBuilder(Contstants.urlChannelServlet);
			sbUrl.append("party=");
			sbUrl.append(channel.getParty());
			sbUrl.append("&service=");
			sbUrl.append(channel.getService());
			sbUrl.append("&channel=");
			sbUrl.append(channel.getChannelName());
			sbUrl.append("&action=");
			sbUrl.append(channel.getActivationState());
			
			System.out.println("sbUrl.toString(): " + sbUrl.toString());
			CreateHuskyUrlConnection createHuskyUrlConnection = new CreateHuskyUrlConnection();
			String response = createHuskyUrlConnection.createUrlConnection(sbUrl.toString());
			if(response.equals(Contstants.success)){
				channel.setUpdateState("UPDATEOK");
			}else{
				channel.setUpdateState("UPDATENOK");
			}
			
		}
//		ObjectMapper objectMapper = new ObjectMapper();
//		System.out.println("Result:::::::: " + objectMapper.writeValueAsString(channelStatusResult));
		
		System.out.println(channelStatusResult.getChannels().getChannel().size());
		
		return channelStatusResult;
	}
}
