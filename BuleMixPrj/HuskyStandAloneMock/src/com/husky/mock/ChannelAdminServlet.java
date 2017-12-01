package com.husky.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.husky.mock.pojo.Channel;
import com.husky.mock.pojo.ChannelStatusResult;

/**
 * Servlet implementation class ChannelAdminServlet
 */
@WebServlet("/ChannelAdminServlet")
public class ChannelAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ChannelAdminServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String party = request.getParameter("party");
		String service = request.getParameter("service");
		String channel = request.getParameter("channel");
		String action = request.getParameter("action");
		
		System.out.println("party: " + party + "\nservice: " + service  + "\nchannel: " + channel + "\naction: " + action);
		
		if(party.equals("*") && service.equals("*") && channel.equals("*") && action.equals("status")){
			//File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\ChannelAdminServlet\\ChannelAdminServlet.xml");
//			File file = new File(Constants.filePath);
//			PrintWriter out = response.getWriter();
//			FileInputStream fis = new FileInputStream(file);
//			int line = 0;
//			while((line = fis.read()) != -1){
//				out.print((char)line);
//			}
//			fis.close();
			
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(Constants.xmlData);
			
		}else if(!service.equals("") && !channel.equals("") && (action.equals(Constants.start) || action.equals(Constants.stop))){
			try {
			//File file = new File(Constants.filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ChannelStatusResult.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ChannelStatusResult ChannelStatusResult = (ChannelStatusResult) jaxbUnmarshaller.unmarshal(new StringReader(Constants.xmlData));
			
			String status = Constants.fail;
			for(Channel jChannel : ChannelStatusResult.getChannels().getChannel()){
				if(jChannel.getParty().equals(party) && jChannel.getService().equals(service) && jChannel.getChannelName().equals(channel)){
					System.out.println("channel: " + channel);
					if(!jChannel.getActivationState().equals(action)){
						jChannel.setActivationState(action);
						status = Constants.success;
					}
				}
			}
			
			jaxbContext = JAXBContext.newInstance(ChannelStatusResult.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// jaxbMarshaller.marshal(customer, file);
			
			//FileOutputStream fos = new FileOutputStream(file);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(ChannelStatusResult, sw);
			Constants.xmlData = sw.toString();
			PrintWriter out = response.getWriter();
			out.write(status);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.write(Constants.fail);
		}

			
			
		}else{
			PrintWriter out = response.getWriter();
			out.print(Constants.error);
		}
		
//		try {
//			File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\ChannelAdminServlet\\ChannelAdminServlet.xml");
//			JAXBContext jaxbContext = JAXBContext.newInstance(ChannelStatusResult.class);
//
//			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//			ChannelStatusResult ChannelStatusResult = (ChannelStatusResult) jaxbUnmarshaller.unmarshal(file);
//			System.out.println(ChannelStatusResult);
//			
//			
//			jaxbContext = JAXBContext.newInstance(ChannelStatusResult.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//			// output pretty printed
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//			// jaxbMarshaller.marshal(customer, file);
//			
//			PrintWriter out = response.getWriter();
//			jaxbMarshaller.marshal(ChannelStatusResult, out);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
