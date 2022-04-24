import React from 'react';
import { ValidatedField } from 'react-jhipster';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Alert, Row, Col, Form } from 'reactstrap';
import { Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import './login-modal.scss';

export interface ILoginModalProps {
  showModal: boolean;
  loginError: boolean;
  handleLogin: (username: string, password: string, rememberMe: boolean) => void;
  handleClose: () => void;
}

const LoginModal = (props: ILoginModalProps) => {
  const login = ({ username, password, rememberMe }) => {
    props.handleLogin(username, password, rememberMe);
  };

  const {
    handleSubmit,
    register,
    formState: { errors, touchedFields },
  } = useForm({ mode: 'onTouched' });

  const { loginError, handleClose } = props;

  const handleLoginSubmit = e => {
    handleSubmit(login)(e);
  };

  return (
    <Modal isOpen={props.showModal} toggle={handleClose} backdrop="static" id="login-page" autoFocus={false}>
      <Form onSubmit={handleLoginSubmit}>
        <ModalHeader id="login-title" data-cy="loginTitle" toggle={handleClose}>
          Đăng nhập
        </ModalHeader>
        <ModalBody>
          <Row>
            <Col md="12">
              {loginError ? (
                <Alert color="danger" data-cy="loginError">
                  <strong>Đăng nhập thất bại!</strong> Vui lòng kiểm tra lại thông tin đăng nhập.
                </Alert>
              ) : null}
            </Col>
            <Col md="12">
              <ValidatedField
                name="username"
                label="Tài khoản"
                placeholder="Tài khoản"
                required
                autoFocus
                data-cy="username"
                validate={{ required: 'Xin mời nhập tài khoản!' }}
                register={register}
                error={errors.username}
                isTouched={touchedFields.username}
              />
              <ValidatedField
                name="password"
                type="password"
                label="Mật khẩu"
                placeholder="Mật khẩu"
                required
                data-cy="password"
                validate={{ required: 'Xin mời nhập mật khẩu!' }}
                register={register}
                error={errors.password}
                isTouched={touchedFields.password}
              />
              <ValidatedField name="rememberMe" type="checkbox" check label="Lưu tài khoản" value={true} register={register} />
            </Col>
          </Row>
          <div className="mt-1">&nbsp;</div>
          <Alert color="warning">
            <Link to="/account/reset/request" data-cy="forgetYourPasswordSelector">
              Có phải bạn đã quên mật khẩu?
            </Link>
          </Alert>
          {/* <Alert color="warning">
            <span>You don&apos;t have an account yet?</span> <Link to="/account/register">Register a new account</Link>
          </Alert> */}
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={handleClose} tabIndex={1}>
            Hủy bỏ
          </Button>{' '}
          <Button color="primary" type="submit" data-cy="submit">
            Đăng nhập
          </Button>
        </ModalFooter>
      </Form>
    </Modal>
  );
};

export default LoginModal;
