<%@ page contentType="text/html; charset=utf-8" %>
			
				<div class="col-lg-8">
					<h2>embed jetty에 work디렉토리에 java파일 남게 설정하는 하는 법</h2>
					<p>
						System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");
						<a href="">WebAppContext webAppContext = new WebAppContext();</a>
						
						webAppContext.setContextPath(contextPath);
						
						webAppContext.setBaseResource(new ResourceCollection(baseResource));
						
						webAppContext.setAttribute("org.eclipse.jetty.webapp.basetempdir", "c:/temp/work");
						
						 
						
						ServletHolder holderJsp = new ServletHolder("jsp",JspServlet.class);
						
						holderJsp.setInitOrder(0);
						
						holderJsp.setInitParameter("keepgenerated","true");
					</p>
				</div>

				
			
