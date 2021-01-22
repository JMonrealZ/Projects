using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Word = Microsoft.Office.Interop.Word;

namespace EdicionWordFilesDemo
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        public static string DOCBASE_PATH = Application.StartupPath + @"\PruebaDoc.docx";
        public static string DOCEDIT_PATH = Application.StartupPath + @"\PruebaDocEdit.pdf";
        public static string DOMICILIO_TXT = @"[Domicilio]";
        public static object NAME_BM = @"Nombre";  //BOOKMARK(nombre del bookmark)
        

        private void btnEditar_Click(object sender, EventArgs e)
        {
            object ObjMiss = System.Reflection.Missing.Value;   //Objeto para abrir word con parametros default

            Word.Application ObjWord = new Word.Application();  //Abrimos word
            Word.Document ObjDoc = ObjWord.Documents.Open((object)DOCBASE_PATH, ObjMiss);   //Abrimos documento
            setTextToBookmark(ObjDoc, NAME_BM, tbName.Text);
            replaceText(ObjDoc, DOMICILIO_TXT, tbDomicilio.Text);
            ObjDoc.SaveAs2(DOCEDIT_PATH, Word.WdSaveFormat.wdFormatPDF,ObjMiss);

            ObjDoc.Close();
            System.Runtime.InteropServices.Marshal.ReleaseComObject(ObjWord);
            //ObjWord.Visible = true;
        }

        public void setTextToBookmark(Word.Document document, object bookmarkName, string text)
        {
            Word.Range bookmarkRange = document.Bookmarks.get_Item(ref bookmarkName).Range;
            bookmarkRange.Text = text;
            object rangeObj = bookmarkRange;
            document.Bookmarks.Add((string)bookmarkName, ref rangeObj);
       
        }

        public void replaceText(Word.Document doc, string oldText, string newText)
        {
            bool t = true;
            bool f = false;
            doc.Content.Find.Execute(oldText, f, t, f, f, f, t, 1, f, newText, 2,f, f, f, f);

        }
    }
}
