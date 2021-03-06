package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    public void testInvoiceDaoSave() {
        //Given
        Item item = new Item(new BigDecimal(100),1,new BigDecimal(70));
        Product product = new Product("Screen");
        item.setProduct(product);
        product.getItems().add(item);
        Invoice invoice = new Invoice("10/12/2019");
        invoice.getItems().add(item);
        item.setInvoice(invoice);
        //When
        invoiceDao.save(invoice);
        List<Invoice> resultList = invoiceDao.findByNumber("10/12/2019");
        int resultSizeList = resultList.size();
        Item resultItem = resultList.get(0).getItems().get(0);
        //Then
        Assert.assertEquals(item.getId(),resultItem.getId());
        Assert.assertEquals(1,resultSizeList);
        //Clean up
        invoiceDao.deleteById(invoice.getId());
    }
}