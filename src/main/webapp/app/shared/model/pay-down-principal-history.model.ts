export interface IPayDownPrincipalHistory {
  id?: number;
  contractId?: number;
  payDownPrincipalAmount?: number;
  payerName?: string | null;
}

export const defaultValue: Readonly<IPayDownPrincipalHistory> = {};
