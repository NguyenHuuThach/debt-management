import dayjs from 'dayjs';

export interface IInterestPay {
  id?: number;
  contractId?: number;
  interestPayDate?: string;
  interestPayAmount?: number;
  payerName?: string | null;
  note?: string | null;
  status?: boolean | null;
}

export const defaultValue: Readonly<IInterestPay> = {
  status: false,
};
