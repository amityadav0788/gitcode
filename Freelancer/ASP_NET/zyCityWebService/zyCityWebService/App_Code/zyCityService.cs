using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Configuration;
using MySql.Data.MySqlClient;

[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]

public class zyCityService : System.Web.Services.WebService
{
    private readonly string _conString = "server=localhost;User Id=root;password=monitor;database=world";

    public zyCityService () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    [WebMethod]  
           
    public DataSet GetCity()
    {
        // Create DataAdapter
        string commandText = "SELECT Name, CountryCode, District, Population FROM CITY";
        MySqlDataAdapter dad = new MySqlDataAdapter(commandText, _conString);
        // Return DataSet
        DataSet dstCity = new DataSet();
        using (dad)
        {
            dad.Fill(dstCity);
        }
        return dstCity;
    }
              
}