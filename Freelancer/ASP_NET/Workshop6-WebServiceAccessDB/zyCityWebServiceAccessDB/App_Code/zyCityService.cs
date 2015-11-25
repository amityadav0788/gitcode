using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Configuration;
using System.Data.OleDb;        //add it for Access Database

[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]

public class zyCityService : System.Web.Services.WebService
{
    private readonly string _conString = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=C:\\Users\\A_K_Yadav\\Downloads\\Workshop6-WebServiceAccessDB\\Northwind.mdb";
    public zyCityService () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    [WebMethod]  
           
    public DataSet GetCity()
    {
        
        // Create DataAdapter
        string CommandText = "SELECT ContactName, ContactTitle, City FROM Customers";
        OleDbDataAdapter dad = new OleDbDataAdapter(CommandText, _conString);
        // Return DataSet
        DataSet dstCity = new DataSet();
        using (dad)
        {
            dad.Fill(dstCity);
        }
        return dstCity;
    }
              
}