package com.example.hw5;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YachtTest {

    Yacht yachtDesign;
    Yacht defaultDesign;

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
        this.yachtDesign =
                new Yacht("Lode Star", 4, 2);

        MockitoAnnotations.initMocks(this);

        when(mockContext.getString(R.string.default_name))
                .thenReturn("Painted Rose");
        when(mockContext.getString(R.string.default_cabins))
                .thenReturn("3");
        when(mockContext.getString(R.string.default_heads))
                .thenReturn("2");

        this.defaultDesign = new Yacht(mockContext);
    }

    @Test
    public void yacht_YachtDesign_ReturnsPrice() {
        double yachtPrice = yachtDesign.determinePrice(2);
        assertThat(yachtPrice).isEqualTo(162000.0);

        yachtPrice = yachtDesign.determinePrice(-5);
        assertThat(yachtPrice).isEqualTo(180000.0);

        yachtPrice = yachtDesign.determinePrice(21);
        assertThat(yachtPrice).isEqualTo(4500.0);
    }

    @Test
    public void yacht_DefaultDesign_ReturnsPrice() {
        double yachtPrice = defaultDesign.determinePrice(3);
        assertThat(yachtPrice).isEqualTo(106250.0);

        yachtPrice = defaultDesign.determinePrice(-1);
        assertThat(yachtPrice).isEqualTo(125000.0);

        yachtPrice = defaultDesign.determinePrice(20);
        assertThat(yachtPrice).isEqualTo(3125.0);
    }

}
