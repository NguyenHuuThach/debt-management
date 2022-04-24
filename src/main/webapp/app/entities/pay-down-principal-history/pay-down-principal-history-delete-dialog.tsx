import React, { useEffect, useState } from 'react';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntity, deleteEntity } from './pay-down-principal-history.reducer';

export const PayDownPrincipalHistoryDeleteDialog = (props: RouteComponentProps<{ id: string }>) => {
  const [loadModal, setLoadModal] = useState(false);
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
    setLoadModal(true);
  }, []);

  const payDownPrincipalHistoryEntity = useAppSelector(state => state.payDownPrincipalHistory.entity);
  const updateSuccess = useAppSelector(state => state.payDownPrincipalHistory.updateSuccess);

  const handleClose = () => {
    props.history.push('/pay-down-principal-history');
  };

  useEffect(() => {
    if (updateSuccess && loadModal) {
      handleClose();
      setLoadModal(false);
    }
  }, [updateSuccess]);

  const confirmDelete = () => {
    dispatch(deleteEntity(payDownPrincipalHistoryEntity.id));
  };

  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="payDownPrincipalHistoryDeleteDialogHeading">
        Confirm delete operation
      </ModalHeader>
      <ModalBody id="debtManagementApp.payDownPrincipalHistory.delete.question">
        Are you sure you want to delete this PayDownPrincipalHistory?
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
        <Button id="jhi-confirm-delete-payDownPrincipalHistory" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Delete
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default PayDownPrincipalHistoryDeleteDialog;
