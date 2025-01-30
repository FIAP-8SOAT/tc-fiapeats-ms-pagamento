//package br.com.fiap.fiapeats.external.persistence.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//import br.com.fiap.fiapeats.domain.utils.Constants;
//import br.com.fiap.fiapeats.domain.utils.GenericUtils;
//import br.com.fiap.fiapeats.external.persistence.mapper.PedidoEntityMapper;
//import br.com.fiap.fiapeats.external.persistence.orm.PedidoEntity;
//import br.com.fiap.fiapeats.external.persistence.repository.PedidoRepositoryJPA;
//import org.apache.logging.log4j.ThreadContext;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class PedidoRepositoryImplTest {
//
//  @InjectMocks private PedidoRepositoryImpl pedidoRepositoryImpl;
//
//  @Mock private PedidoEntityMapper pedidoMapper;
//
//  @Mock private PedidoRepositoryJPA pedidoRepositoryJPA;
//
//  private Pedido pedido;
//  private PedidoEntity pedidoEntity;
//  private CriarPedidoResponse criarPedidoResponse;
//
//  @BeforeEach
//  void setUp() {
//    pedido = GenericUtils.retornaPedidoValido();
//    pedidoEntity = GenericUtils.retornaPedidoEntityValido();
//    criarPedidoResponse = GenericUtils.retornaPedidoResponseValido();
//  }
//
//  @Test
//  void salvarPedidoComSucesso() {
//    when(pedidoMapper.toPedidoEntity(any(Pedido.class))).thenReturn(pedidoEntity);
//    when(pedidoRepositoryJPA.save(any(PedidoEntity.class))).thenReturn(pedidoEntity);
//    when(pedidoMapper.toPedidoFromEntity(any(PedidoEntity.class))).thenReturn(pedido);
//    assertNotNull(pedidoRepositoryImpl.salvarPedido(pedido));
//    verify(pedidoRepositoryJPA, times(1)).save(any(PedidoEntity.class));
//  }
//
//  @Test
//  void salvarPedidoMappersComSucesso() {
//    when(pedidoMapper.toPedidoEntity(any(Pedido.class))).thenReturn(pedidoEntity);
//    when(pedidoRepositoryJPA.save(any(PedidoEntity.class))).thenReturn(pedidoEntity);
//    when(pedidoMapper.toPedidoFromEntity(any(PedidoEntity.class))).thenReturn(pedido);
//    pedidoRepositoryImpl.salvarPedido(pedido);
//    verify(pedidoMapper, times(1)).toPedidoEntity(any(Pedido.class));
//    verify(pedidoMapper, times(1)).toPedidoFromEntity(any(PedidoEntity.class));
//  }
//
//  @Test
//  void DeveAdicionarCorrelationIdNoLog() {
//    when(pedidoMapper.toPedidoEntity(any(Pedido.class))).thenReturn(pedidoEntity);
//    when(pedidoRepositoryJPA.save(any(PedidoEntity.class))).thenReturn(pedidoEntity);
//    ThreadContext.put(Constants.CORRELATION_ID, "correlation-id");
//    pedidoRepositoryImpl.salvarPedido(pedido);
//    assertEquals("correlation-id", ThreadContext.get(Constants.CORRELATION_ID));
//  }
//
//  @Test
//  void recusaSalvaPedidoSemLista() {
//    Pedido pedidoSemProdutos = GenericUtils.pedidoInvalidoSemProdutos();
//    assertNull(pedidoRepositoryImpl.salvarPedido(pedidoSemProdutos));
//    verify(pedidoRepositoryJPA, times(0)).save(any(PedidoEntity.class));
//  }
//}
