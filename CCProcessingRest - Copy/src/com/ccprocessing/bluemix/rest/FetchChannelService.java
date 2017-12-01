package com.ccprocessing.bluemix.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.husky.mock.pojo.ChannelStatusResult;

@Path("/FetchChannelService")
public class FetchChannelService {

	@GET
	@Path("/fetchChannels")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchChannels() {
		try {
			String url = "http://localhost:8080/HuskyStandAloneMock/ChannelAdminServlet?party=*&service=*&channel=*&action=status";

			URL objUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			// con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ChannelStatusResult.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ChannelStatusResult ChannelStatusResult = (ChannelStatusResult) jaxbUnmarshaller.unmarshal(new StringReader(response.toString()));

			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println(objectMapper.writeValueAsString(ChannelStatusResult));
			return objectMapper.writeValueAsString(ChannelStatusResult);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
